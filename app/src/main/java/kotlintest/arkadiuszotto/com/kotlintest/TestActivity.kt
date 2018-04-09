package kotlintest.arkadiuszotto.com.kotlintest

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.InputFilter
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import kotlintest.arkadiuszotto.com.kotlintest.Model.Test.*
import java.util.*

class TestActivity : AppCompatActivity() {

    companion object {
        const val PERFORMANCE_TEST_TYPE_KEY = "PERFORMANCE_TEST_TYPE"
        const val PERFORMANCE_TEST_ITERATIONS_KEY = "PERFORMANCE_TEST_ITERATIONS"


        const val DEFAULT_NUMBER_OF_ITERATIONS = 1000000
    }

    private lateinit var resultDescriptionTextView: TextView
    private lateinit var resultTextView: TextView

    private lateinit var iterationsTextField: EditText

    private lateinit var performanceTest: PerformanceTest

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)

        setContentView(R.layout.activity_test)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        when (intent.getSerializableExtra(PERFORMANCE_TEST_TYPE_KEY) as PerformanceTestType) {
            PerformanceTestType.LIST_ADD -> {
                performanceTest = ListAddTest()
                setTitle(R.string.activity_test_title_list_add)
            }
            PerformanceTestType.LIST_SORT -> {
                performanceTest = ListSortTest()
                setTitle(R.string.activity_test_title_list_sort)
            }
            PerformanceTestType.STRINGS -> {
                performanceTest = StringsTest()
                setTitle(R.string.activity_test_title_strings)
            }
            PerformanceTestType.CLASSES -> {
                performanceTest = ClassesTest()
                setTitle(R.string.activity_test_title_class)
            }
        }

        resultDescriptionTextView = findViewById(R.id.text_view_result_description)
        resultTextView = findViewById(R.id.text_view_result)

        iterationsTextField = findViewById(R.id.text_field_iterations)

        val maxNumberOfIterations = intent.getIntExtra(PERFORMANCE_TEST_ITERATIONS_KEY, 9)

        iterationsTextField.filters = arrayOf<InputFilter>(InputFilter.LengthFilter(maxNumberOfIterations))

        resultDescriptionTextView.visibility = View.INVISIBLE
        resultTextView.visibility = View.INVISIBLE


        findViewById<Button>(R.id.button_run_test).setOnClickListener {
            runTest()
        }
        findViewById<Button>(R.id.button_default_iterations).setOnClickListener {
            iterationsTextField.setText(DEFAULT_NUMBER_OF_ITERATIONS.toString())
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }

    private fun runTest() {
        val iterations = iterationsTextField.text.toString().toLongOrNull() ?: 0
        val testResult = performanceTest.run(iterations)
        resultTextView.text = String.format(Locale.getDefault(), "%.6f", testResult)
        resultDescriptionTextView.visibility = View.VISIBLE
        resultTextView.visibility = View.VISIBLE
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        return true
    }
}
