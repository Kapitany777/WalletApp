package eu.braincluster.walletapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import eu.braincluster.walletapp.data.Wallet
import eu.braincluster.walletapp.databinding.ListItemWalletBinding

class WalletAdapter(private var walletList: List<Wallet>) : RecyclerView.Adapter<WalletAdapter.ListItemHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListItemHolder
    {
        val binding = ListItemWalletBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ListItemHolder(binding)
    }

    override fun getItemCount(): Int
    {
        return walletList.size
    }

    override fun onBindViewHolder(holder: ListItemHolder, position: Int)
    {
        var wallet = walletList[position]

        holder.bind(wallet)
    }

    class ListItemHolder(private var binding: ListItemWalletBinding) : RecyclerView.ViewHolder(binding.root)
    {
        fun bind(wallet: Wallet)
        {
            binding.textViewType.text = wallet.type
            binding.textViewDescription.text = wallet.description
            binding.textViewAmount.text = wallet.amount.toString()
        }
    }
}
