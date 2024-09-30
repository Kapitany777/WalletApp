package eu.braincluster.walletapp.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface WalletDao
{
    @Query("select * from wallet order by walletId")
    fun getAllWallet(): List<Wallet>

    @Insert
    fun addWallet(wallet: Wallet): Long

    @Delete
    fun deleteWallet(wallet: Wallet)

    @Update
    fun updateWallet(wallet: Wallet)

    @Query("select sum(amount) from wallet where type = 'income'")
    fun getIncome(): Int

    @Query("select sum(amount) from wallet where type = 'expense'")
    fun getExpense(): Int
}
