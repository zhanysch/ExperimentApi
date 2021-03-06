package com.example.newschoco.ui.bottomNavigation.home.headlineDetails

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.newschoco.R
import com.example.newschoco.databinding.FragmentHeadlinesDetailsBinding
import com.example.newschoco.utils.viewBinding
import com.squareup.picasso.Picasso
import org.koin.androidx.viewmodel.ext.android.viewModel

class HeadLinesDetailsFragment : Fragment(R.layout.fragment_headlines_details) {

    private val binding by viewBinding(FragmentHeadlinesDetailsBinding::bind)

    private val vm by viewModel<HeadLineDetailsViewModel>()

    private val args : HeadLinesDetailsFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
    }

    private fun setupViews() {
        binding.tvTitle.text = args.details?.title
        binding.tvDesc.text = args.details?.description
        binding.tvContent.text = args.details?.content
        binding.tvAuthor.text = args.details?.author
        binding.tvSource.text = args.details?.source?.name
        binding.tvURL.text = args.details?.url
        binding.tvPublishedAt.text = args.details?.publishedAt
        val image = args.details?.urlToImage
        Picasso.get().load(image).into(binding.ivURLTolmage)

        binding.btnBack.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.checkNews.setOnCheckedChangeListener{ buttonView, isCheked ->
            args.details?.isChecked = isCheked
            args.details?.let { vm.update(it) }
        }
    }
}