package kotlintest.arkadiuszotto.com.kotlintest.Fragment

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import kotlintest.arkadiuszotto.com.kotlintest.Model.Test.PerformanceTestType
import kotlintest.arkadiuszotto.com.kotlintest.R
import kotlintest.arkadiuszotto.com.kotlintest.TestActivity

/**
 * @author arekotto
 */
class TestsFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreate(savedInstanceState)

        val inflate = inflater.inflate(R.layout.fragment_tests, container, false)
        val intent = Intent(activity, TestActivity::class.java)

        inflate.findViewById<Button>(R.id.button_test_array_add).setOnClickListener {
            intent.putExtra(TestActivity.PERFORMANCE_TEST_TYPE_KEY, PerformanceTestType.LIST_ADD)
            intent.putExtra(TestActivity.PERFORMANCE_TEST_ITERATIONS_KEY, 9)
            activity?.startActivity(intent)
        }
        inflate.findViewById<Button>(R.id.button_test_array_sort).setOnClickListener {
            intent.putExtra(TestActivity.PERFORMANCE_TEST_TYPE_KEY, PerformanceTestType.LIST_SORT)
            intent.putExtra(TestActivity.PERFORMANCE_TEST_ITERATIONS_KEY, 7)
            activity?.startActivity(intent)
        }
        inflate.findViewById<Button>(R.id.button_test_string).setOnClickListener {
            intent.putExtra(TestActivity.PERFORMANCE_TEST_TYPE_KEY, PerformanceTestType.STRINGS)
            intent.putExtra(TestActivity.PERFORMANCE_TEST_ITERATIONS_KEY, 9)
            activity?.startActivity(intent)
        }
        inflate.findViewById<Button>(R.id.button_test_classes).setOnClickListener {
            intent.putExtra(TestActivity.PERFORMANCE_TEST_TYPE_KEY, PerformanceTestType.CLASSES)
            intent.putExtra(TestActivity.PERFORMANCE_TEST_ITERATIONS_KEY, 9)
            activity?.startActivity(intent)
        }
        return inflate
    }
}