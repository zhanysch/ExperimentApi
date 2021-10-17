package com.example.newschoco.ui.bottomNavigation.home.everythingDetails

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.newschoco.R
import com.example.newschoco.databinding.FragmentEverythingDetailsBinding
import com.example.newschoco.utils.viewBinding
import com.squareup.picasso.Picasso

class EverythingDetailsFragment():Fragment(R.layout.fragment_everything_details) {

    private val binding by viewBinding(FragmentEverythingDetailsBinding::bind)
    private val args : EverythingDetailsFragmentArgs by navArgs()

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

    }
}