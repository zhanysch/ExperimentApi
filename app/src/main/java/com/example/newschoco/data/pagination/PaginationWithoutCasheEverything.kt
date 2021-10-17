package com.example.newschoco.data.pagination

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.newschoco.data.model.everything.EverythingArticles
import com.example.newschoco.data.remote.NewsService
import retrofit2.HttpException
import java.io.IOException

private const val PARAM_QUERY = "in:name,description"

class PaginationWithoutCasheEverything(
    private val service: NewsService,
    private val query: String
):  PagingSource<Int, EverythingArticles>(){

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, EverythingArticles> {
        val page = params.key ?: START_PAGE
        val apiQuery = query

        return try {
            val response = service.getAllNews(
                query = query,
                sortBy ="popularity",
                language = "en",
                page=page,
                params.loadSize
            )
            val itemsCount = response.articles.size

            val nextPage = if (itemsCount == 0) {
                null
            } else {
                page + (params.loadSize / PAGE_SIZE)
            }
            LoadResult.Page(
                data = response.articles,
                prevKey = if (page == START_PAGE) null else page - 1,
                nextKey = nextPage

            )
        } catch (e: IOException) {
            LoadResult.Error(e)

        } catch (e: HttpException) {
            LoadResult.Error(e)
        }

    }

    override fun getRefreshKey(state: PagingState<Int, EverythingArticles>): Int? {
        TODO("Not yet implemented")
    }

    companion object{
        const val  PAGE_SIZE = 20
    }


}