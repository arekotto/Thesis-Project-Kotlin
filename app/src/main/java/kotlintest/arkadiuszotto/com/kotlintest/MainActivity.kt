package kotlintest.arkadiuszotto.com.kotlintest

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import kotlintest.arkadiuszotto.com.kotlintest.Fragment.DrawFragment
import kotlintest.arkadiuszotto.com.kotlintest.Fragment.RandomUserFragment
import kotlintest.arkadiuszotto.com.kotlintest.Fragment.TestsFragment
import kotlintest.arkadiuszotto.com.kotlintest.Fragment.WebSocketFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var currentFragment: Fragment? = null

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_tests -> {
                setTitle(R.string.activity_main_title)
                replaceFragment<TestsFragment>()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_web_sockets -> {
                setTitle(R.string.title_web_sockets)
                replaceFragment<WebSocketFragment>()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_drawing -> {
                setTitle(R.string.title_drawing)
                replaceFragment<DrawFragment>()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_json_mapping -> {
                setTitle(R.string.title_random_users)
                replaceFragment<RandomUserFragment>()
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    private inline fun <reified F : Fragment> replaceFragment() {
        val tag = F::class.java.simpleName

        supportFragmentManager.beginTransaction()
                .apply {
                    currentFragment?.run(::hide)
                    currentFragment = supportFragmentManager.findFragmentByTag(tag)?.also { show(it) } ?:
                            F::class.java.newInstance().also { add(R.id.content, it, tag) }
                }
                .commit()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        navigation.selectedItemId = R.id.navigation_tests
    }
}