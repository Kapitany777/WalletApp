package eu.braincluster.walletapp.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface WalletDao
{
    @Query("SELECT * FROM wallet")
    fun getAllWallet() : List<Wallet>

    @Insert
    fun addWallet(wallet: Wallet): Long

    @Delete
    fun deleteWallet(wallet: Wallet)

    @Update
    fun updateWallet(wallet: Wallet)
}
