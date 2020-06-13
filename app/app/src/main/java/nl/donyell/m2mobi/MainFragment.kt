package nl.donyell.m2mobi

import InjectorUtils
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import nl.donyell.m2mobi.adapter.PhotoAdapter
import nl.donyell.m2mobi.databinding.FragmentMainBinding
import nl.donyell.m2mobi.viewmodel.main.MainViewModel

class MainFragment : Fragment() {

    private val mainViewModel: MainViewModel by viewModels {
        InjectorUtils.provideMainViewModelFactory(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = DataBindingUtil.inflate<FragmentMainBinding>(
            inflater, R.layout.fragment_main, container, false
        )
//            .apply {
//                viewModel = mainViewModel
//            }

        val adapter = PhotoAdapter()
        binding.rvPhotos.adapter = adapter

        NetworkModule.service.getPhotos()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ photosResponse ->
                adapter.submitList(photosResponse.sortedBy { it.title })
            }, {
                Log.d("Main", "wa3hed error: $it")
            })

        return binding.root
    }

//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//
//        button.setOnClickListener {
//            val action = MainFragmentDirections.actionMainFragmentToDetailFragment(4)
//            view.findNavController().navigate(action)
//        }
//    }

}