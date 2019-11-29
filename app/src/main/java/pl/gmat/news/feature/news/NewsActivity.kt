package pl.gmat.news.feature.news

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
import pl.gmat.news.databinding.ActivityNewsBinding
import pl.gmat.news.di.Injector
import pl.gmat.news.feature.news.widget.NewsAdapter
import javax.inject.Inject
import javax.inject.Provider

class NewsActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelProvider: Provider<NewsViewModel>

    private val viewModel: NewsViewModel by viewModels {
        object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>) = viewModelProvider.get() as T
        }
    }

    private var binding: ActivityNewsBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        Injector.appComponent.newsComponentFactory().create(this).inject(this)
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView<ActivityNewsBinding>(this, R.layout.activity_news)
            .apply {
                state = this@NewsActivity.viewModel.state
                viewModel = this@NewsActivity.viewModel
                lifecycleOwner = this@NewsActivity
                newsRecyclerView.adapter =
                    NewsAdapter(this@NewsActivity.viewModel)
                newsRecyclerView.layoutManager = LinearLayoutManager(this@NewsActivity)
            }
        viewModel.effect.observe(this, Observer { handleEffect(it) })
    }

    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }

    private fun handleEffect(effect: NewsEffect) = when (effect) {
        is NewsEffect.ShowNews -> Unit
        is NewsEffect.ShowRefreshError -> showErrorSnackBar(effect.error)
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
