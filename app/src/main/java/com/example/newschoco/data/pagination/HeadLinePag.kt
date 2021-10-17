package com.example.newschoco.data.pagination

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.newschoco.data.db.AppDataBase
import com.example.newschoco.data.model.headline.Articles
import com.example.newschoco.data.model.headline.PageKeys

import com.example.newschoco.data.remote.NewsService
import retrofit2.HttpException
import java.io.IOException

const val START_PAGE = 1

@ExperimentalPagingApi
class HeadLinePag(
    private val service: NewsService,
    private val db: AppDataBase,
) : RemoteMediator<Int, Articles>() {

    override suspend fun load(loadType: LoadType, state: PagingState<Int, Articles>): MediatorResult {
        val page = when (loadType) {
            LoadType.REFRESH -> {
                val remoteKeys = getRemoteKeysToCurrentPosition(state)
                remoteKeys?.nextKey?.minus(1) ?: START_PAGE
            }

            LoadType.PREPEND -> {
                val remoteKeys = getRemoteKeyforFirstItem(state)
                remoteKeys?.prevKey ?: return MediatorResult.Success(
                    endOfPaginationReached = true
                )
                remoteKeys.prevKey
            }

            LoadType.APPEND -> {
                val remoteKeys = getRemoteKeysForLastItem(state)
                remoteKeys?.nextKey ?: 2
            }
        }

        try {
            Log.d("gdfgd", page.toString())
            val apiResponse = service.getHeadLines(country ="us", category ="business",page=page,pageSize = state.config.pageSize)
            val endofPaginationReached = apiResponse.articles.isEmpty()

            db.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    db.getContentDao().deleteAll()
                    db.getPagingKeysDao().deleteAll()
                }

                val prevKey = if (page == START_PAGE) null else page - 1
                val nextKey = if (endofPaginationReached) null else page + 1

                val keys = apiResponse.articles.map {
                    PageKeys(id = it.id, prevKey = prevKey,nextKey = nextKey)
                }

                db.getPagingKeysDao().insertAll(keys)
                db.getContentDao().insert(apiResponse.articles)

            }

            return MediatorResult.Success(endOfPaginationReached = endofPaginationReached)

        } catch (e : IOException){
            return MediatorResult.Error(e)
        } catch (e: HttpException){
            return MediatorResult.Error(e)
        }
    }

    private suspend fun getRemoteKeysToCurrentPosition(
        state: PagingState<Int, Articles>,
    ): PageKeys? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id.let { repoId ->
                db.getPagingKeysDao().getKeyId(repoId)
            }
        }
    }

    private suspend fun getRemoteKeyforFirstItem(state: PagingState<Int, Articles>): PageKeys? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let { repo ->
                db.getPagingKeysDao().getKeyId(repo.id)
            }
    }

    private suspend fun getRemoteKeysForLastItem(state: PagingState<Int,Articles>): PageKeys? {
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let { repo ->
                db.getPagingKeysDao().getKeyId(repo.id)
            }
    }
}
