package eu.braincluster.walletapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import eu.braincluster.walletapp.adapter.WalletAdapter
import eu.braincluster.walletapp.data.Wallet
import eu.braincluster.walletapp.data.WalletDatabase
import eu.braincluster.walletapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity()
{
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.getRoot())

        initializeComponent()
    }

    private fun initializeComponent()
    {
        binding.buttonSave.setOnClickListener {
            saveWalletData()
        }

        binding.buttonBalance.setOnClickListener {
            val intent = Intent(this, BalanceActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume()
    {
        super.onResume()

        readWalletData()
    }

    private fun readWalletData()
    {
        Thread {
            var walletData = WalletDatabase.getInstance(this@MainActivity).walletDao().getAllWallet()

            runOnUiThread {
                binding.recyclerViewWallet.itemAnimator = DefaultItemAnimator()
                binding.recyclerViewWallet.addItemDecoration(DividerItemDecoration(this@MainActivity, LinearLayoutManager.VERTICAL))
                binding.recyclerViewWallet.adapter = WalletAdapter(walletData)
            }
        }.start()
    }

    private fun saveWalletData()
    {
        val description = binding.editTextDescription.text.toString()
        val amountText = binding.editTextAmount.text.toString()

        val type = if (binding.toggleButtonType.isChecked) "income" else "expense"

        if (description.isEmpty())
        {
            binding.editTextDescription.error = "Description data is required"
            return
        }

        if (amountText.isEmpty())
        {
            binding.editTextAmount.error = "Amount data is required"
            return
        }

        val amount = amountText.toInt()

        val wallet = Wallet(null, description, amount, type)

        Thread {
            val dao = WalletDatabase.getInstance(this@MainActivity).walletDao()

            dao.addWallet(wallet)

            var walletData = dao.getAllWallet()

            runOnUiThread {
                binding.editTextDescription.setText("")
                binding.editTextAmount.setText("")

                binding.recyclerViewWallet.adapter = WalletAdapter(walletData)

                binding.editTextDescription.requestFocus()
            }
        }.start()
    }
}
