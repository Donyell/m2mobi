import android.content.Context
import nl.donyell.m2mobi.viewmodel.main.MainViewModelFactory

object InjectorUtils {
    fun provideMainViewModelFactory(
        context: Context
    ): MainViewModelFactory {
        return MainViewModelFactory(context)
    }
}