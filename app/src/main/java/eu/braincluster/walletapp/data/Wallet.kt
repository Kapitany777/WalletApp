package eu.braincluster.walletapp.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "wallet")
data class Wallet(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "walletId")
    var walletId: Long?,

    @ColumnInfo(name = "description")
    var description: String,

    @ColumnInfo(name = "amount")
    var amount: Int,

    @ColumnInfo(name = "type")
    var type: String
)
{
}
