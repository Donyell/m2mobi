package nl.donyell.m2mobi.presentation.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.google.android.material.snackbar.Snackbar
import nl.donyell.m2mobi.M2MobiApp
import nl.donyell.m2mobi.R
import nl.donyell.m2mobi.databinding.FragmentMainBinding
import nl.donyell.m2mobi.presentation.adapter.PhotoAdapter
import nl.donyell.m2mobi.presentation.navigator.MainNavigationAction.OpenDetail
import nl.donyell.m2mobi.presentation.viewmodel.MainViewModel
import javax.inject.Inject

class MainFragment : Fragment() {

    @Inject
    lateinit var mainViewModel: MainViewModel

    private val adapter by lazy {
        PhotoAdapter().apply {
            onPhotoClick = {
                mainViewModel.onPhotoClicked(it)
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (context.applicationContext as M2MobiApp).appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = DataBindingUtil.inflate<FragmentMainBinding>(
            inflater, R.layout.fragment_main, container, false
        )

        binding.viewModel = mainViewModel
        binding.lifecycleOwner = this
        binding.rvPhotos.adapter = adapter

        observeViewModel(binding)

        return binding.root
    }

    private fun observeViewModel(binding: FragmentMainBinding) {
        mainViewModel.photos.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })

        mainViewModel.errorMessage.observe(viewLifecycleOwner, Observer { errorMessage ->
            errorMessage?.let {
                Snackbar.make(binding.root, it, Snackbar.LENGTH_LONG).show()
            }
        })

        mainViewModel.navigation.observe(viewLifecycleOwner, Observer {
            if (it is OpenDetail) {
                openDetail(it)
            }
        })
    }

    private fun openDetail(action: OpenDetail) {
        val direction = MainFragmentDirections.actionMainFragmentToDetailFragment(
            action.photo.id,
            action.photo.title,
            action.photo.imageUrl
        )
        view?.findNavController()?.navigate(direction)
    }
}
