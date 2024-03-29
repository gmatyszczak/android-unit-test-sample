package pl.gmat.news.feature.news

import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.FlowPreview
import pl.gmat.news.R
import pl.gmat.news.common.feature.BaseActivity
import pl.gmat.news.databinding.ActivityNewsBinding
import pl.gmat.news.di.AppComponent
import pl.gmat.news.feature.details.NewsDetailsActivity
import pl.gmat.news.feature.news.widget.NewsAdapter

@FlowPreview
class NewsActivity : BaseActivity<ActivityNewsBinding, NewsState, NewsEffect, NewsViewModel>() {

    override val viewModelClass = NewsViewModel::class.java
    override val layoutId = R.layout.activity_news

    override fun ActivityNewsBinding.setupBinding() {
        state = this@NewsActivity.viewModel.state
        viewModel = this@NewsActivity.viewModel
        lifecycleOwner = this@NewsActivity
        newsRecyclerView.apply {
            adapter = NewsAdapter(this@NewsActivity.viewModel)
            layoutManager = LinearLayoutManager(this@NewsActivity)
        }
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }

    override fun inject(appComponent: AppComponent) =
        appComponent.newsComponentFactory().create(this).inject(this)

    override fun handleEffect(effect: NewsEffect) = when (effect) {
        is NewsEffect.ShowNewsDetails -> startActivity(
            NewsDetailsActivity.createIntent(
                this,
                effect.news
            )
        )
        is NewsEffect.ShowRefreshError -> showErrorSnackBar()
    }

    private fun showErrorSnackBar() {
        binding?.let {
            Snackbar.make(
                it.newsRecyclerView,
                getString(R.string.news_refresh_error_message),
                Snackbar.LENGTH_LONG
            ).show()
        }
    }
}
