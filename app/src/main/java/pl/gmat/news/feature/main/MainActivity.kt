package pl.gmat.news.feature.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import pl.gmat.news.R
import pl.gmat.news.databinding.ActivityMainBinding
import pl.gmat.news.di.Injector
import pl.gmat.news.feature.main.widget.NewsAdapter
import javax.inject.Inject
import javax.inject.Provider

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelProvider: Provider<MainViewModel>

    private val viewModel: MainViewModel by viewModels {
        object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>) = viewModelProvider.get() as T
        }
    }

    private var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        Injector.appComponent.mainComponentFactory().create(this).inject(this)
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
            .apply {
                state = this@MainActivity.viewModel.state
                viewModel = this@MainActivity.viewModel
                lifecycleOwner = this@MainActivity
                newsRecyclerView.adapter =
                    NewsAdapter(this@MainActivity.viewModel)
                newsRecyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
            }
        viewModel.effect.observe(this, Observer { handleEffect(it) })
    }

    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }

    private fun handleEffect(effect: MainEffect) = when (effect) {
        is MainEffect.ShowNews -> Unit
        is MainEffect.ShowRefreshError -> showErrorSnackBar(effect.error)
    }

    private fun showErrorSnackBar(error: Throwable) {
        binding?.let {
            Snackbar.make(
                it.newsRecyclerView,
                getString(R.string.news_refresh_error_message, error.message),
                Snackbar.LENGTH_LONG
            ).show()
        }
    }
}
