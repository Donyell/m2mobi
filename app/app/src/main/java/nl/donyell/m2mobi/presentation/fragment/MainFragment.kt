package nl.donyell.m2mobi.presentation.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import nl.donyell.m2mobi.M2MobiApp
import nl.donyell.m2mobi.R
import nl.donyell.m2mobi.presentation.adapter.PhotoAdapter
import nl.donyell.m2mobi.databinding.FragmentMainBinding
import nl.donyell.m2mobi.presentation.viewmodel.main.MainViewModel
import javax.inject.Inject

class MainFragment : Fragment() {

    @Inject
    lateinit var mainViewModel: MainViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (context.applicationContext as M2MobiApp).appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = DataBindingUtil.inflate<FragmentMainBinding>(
            inflater, R.layout.fragment_main, container, false
        )

        val adapter = PhotoAdapter()
        binding.rvPhotos.adapter = adapter

        mainViewModel.photos.observe(viewLifecycleOwner, Observer { photos ->
            adapter.submitList(photos)
        })

        return binding.root
    }
}