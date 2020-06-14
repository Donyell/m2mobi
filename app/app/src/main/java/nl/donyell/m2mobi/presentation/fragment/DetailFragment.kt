package nl.donyell.m2mobi.presentation.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import nl.donyell.m2mobi.M2MobiApp
import nl.donyell.m2mobi.R
import nl.donyell.m2mobi.databinding.FragmentDetailBinding
import nl.donyell.m2mobi.presentation.adapter.CommentAdapter
import nl.donyell.m2mobi.presentation.viewmodel.DetailViewModel
import javax.inject.Inject

class DetailFragment : Fragment() {

    private val args: DetailFragmentArgs by navArgs()

    @Inject
    lateinit var detailViewModel: DetailViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (context.applicationContext as M2MobiApp).appComponent.inject(this)

        val photoId: Long = args.id
        val title: String = args.title
        val imageUrl: String = args.imageUrl

        detailViewModel.onAttach(photoId, title, imageUrl)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = DataBindingUtil.inflate<FragmentDetailBinding>(
            inflater, R.layout.fragment_detail, container, false
        )
        binding.viewModel = detailViewModel

        val adapter = CommentAdapter()
        binding.rvComments.adapter = adapter

        detailViewModel.comments.observe(viewLifecycleOwner, Observer { comments ->
            adapter.submitList(comments)
        })
        return binding.root
    }
}