package pl.gmat.news.feature.details_final
//
//import android.content.Context
//import android.content.Intent
//import androidx.recyclerview.widget.DividerItemDecoration
//import androidx.recyclerview.widget.LinearLayoutManager
//import pl.gmat.news.R
//import pl.gmat.news.common.feature.BaseActivity
//import pl.gmat.news.common.model.News
//import pl.gmat.news.databinding.ActivityNewsDetailsBinding
//import pl.gmat.news.common.di.AppComponent
//import pl.gmat.news.feature.details.widget.CommentsAdapter
//
//class NewsDetailsActivity :
//    BaseActivity<ActivityNewsDetailsBinding, NewsDetailsState, NewsDetailsEffect, NewsDetailsViewModel>() {
//
//    companion object {
//        const val EXTRA_NEWS = "EXTRA_NEWS"
//
//        fun createIntent(context: Context, news: News) =
//            Intent(context, NewsDetailsActivity::class.java).apply {
//                putExtra(EXTRA_NEWS, news)
//            }
//    }
//
//    override val viewModelClass = NewsDetailsViewModel::class.java
//    override val layoutId = R.layout.final_activity_news_details
//
//    override fun ActivityNewsDetailsBinding.setupBinding() {
//        state = this@NewsDetailsActivity.viewModel.state
//        viewModel = this@NewsDetailsActivity.viewModel
//        lifecycleOwner = this@NewsDetailsActivity
//        commentsRecyclerView.apply {
//            adapter = CommentsAdapter()
//            layoutManager = LinearLayoutManager(this@NewsDetailsActivity)
//            addItemDecoration(
//                DividerItemDecoration(
//                    this@NewsDetailsActivity,
//                    DividerItemDecoration.VERTICAL
//                )
//            )
//        }
//    }
//
//    override fun inject(appComponent: AppComponent) =
//        appComponent.newsDetailsComponentFactory().create(this).inject(this)
//
//    override fun handleEffect(effect: NewsDetailsEffect) = Unit
//
//}
