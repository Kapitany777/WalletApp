package eu.braincluster.walletapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "wallet")
data class Wallet(
    @PrimaryKey(autoGenerate = true) var walletId: Long?
)
{
}