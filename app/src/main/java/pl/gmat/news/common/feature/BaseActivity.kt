package pl.gmat.news.common.feature

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import pl.gmat.news.di.AppComponent
import pl.gmat.news.di.Injector
import javax.inject.Inject
import javax.inject.Provider

abstract class BaseActivity<Binding : ViewDataBinding, State, Effect, ViewModel : BaseViewModel<State, Effect>> :
    AppCompatActivity() {

    @Inject
    lateinit var viewModelProvider: Provider<ViewModel>

    protected abstract val viewModelClass: Class<ViewModel>
    protected abstract val layoutId: Int

    protected lateinit var viewModel: ViewModel

    protected var binding: Binding? = null

    protected abstract fun handleEffect(effect: Effect)
    protected abstract fun Binding.setupBinding()
    protected abstract fun inject(appComponent: AppComponent)

    private val viewModelFactory = object : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : androidx.lifecycle.ViewModel> create(modelClass: Class<T>): T {
            return viewModelProvider.get() as T
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        inject(Injector.appComponent)
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory)[viewModelClass]
        binding = DataBindingUtil.setContentView<Binding>(this, layoutId).apply {
            setupBinding()
        }
        viewModel.effect.observe(this, Observer { handleEffect(it) })
    }

    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }
}