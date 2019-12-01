package pl.gmat.news.feature.details

import android.content.Context
import android.content.Intent
import pl.gmat.news.R
import pl.gmat.news.common.feature.BaseActivity
import pl.gmat.news.databinding.ActivityNewsDetailsBinding
import pl.gmat.news.di.AppComponent

class NewsDetailsActivity :
    BaseActivity<ActivityNewsDetailsBinding, NewsDetailsState, NewsDetailsEffect, NewsDetailsViewModel>() {

    companion object {
        fun createIntent(context: Context) = Intent(context, NewsDetailsActivity::class.java)
    }

    override val viewModelClass = NewsDetailsViewModel::class.java
    override val layoutId = R.layout.activity_news_details

    override fun ActivityNewsDetailsBinding.setupBinding() {
        state = this@NewsDetailsActivity.viewModel.state
        viewModel = this@NewsDetailsActivity.viewModel
        lifecycleOwner = this@NewsDetailsActivity
    }

    override fun inject(appComponent: AppComponent) =
        appComponent.newsDetailsComponentFactory().create(this).inject(this)

    override fun handleEffect(effect: NewsDetailsEffect) = Unit

}
