package eu.braincluster.walletapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import eu.braincluster.walletapp.data.WalletDatabase
import eu.braincluster.walletapp.databinding.ActivityBalanceBinding

class BalanceActivity : AppCompatActivity()
{
    private lateinit var binding: ActivityBalanceBinding

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)

        binding = ActivityBalanceBinding.inflate(layoutInflater)
        setContentView(binding.getRoot())
    }

    override fun onResume()
    {
        super.onResume()

        Thread {
            val dao = WalletDatabase.getInstance(this@BalanceActivity).walletDao()

            val income = dao.getIncome()
            val expense = dao.getExpense()
            val balance = income - expense

            runOnUiThread {
                binding.textViewIncome.text = income.toString()
                binding.textViewExpense.text = expense.toString()
                binding.textViewBalance.text = balance.toString()
            }
        }.start()
    }
}