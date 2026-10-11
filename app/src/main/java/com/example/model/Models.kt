package com.example.model

enum class CryptoNetwork(
    val id: String,
    val displayName: String,
    val chainFullName: String,
    val feeUsdt: Double,
    val confirmationTime: String,
    val addressPrefixHint: String
) {
    BEP20(
        id = "BEP-20",
        displayName = "BEP-20",
        chainFullName = "BNB Smart Chain (BSC)",
        feeUsdt = 0.30,
        confirmationTime = "~1-2 menit",
        addressPrefixHint = "0x..."
    ),
    TRC20(
        id = "TRC-20",
        displayName = "TRC-20",
        chainFullName = "TRON Network",
        feeUsdt = 1.00,
        confirmationTime = "~2-3 menit",
        addressPrefixHint = "T..."
    ),
    ERC20(
        id = "ERC-20",
        displayName = "ERC-20",
        chainFullName = "Ethereum Mainnet",
        feeUsdt = 3.50,
        confirmationTime = "~5-10 menit",
        addressPrefixHint = "0x..."
    ),
    POLYGON(
        id = "POLYGON",
        displayName = "Polygon",
        chainFullName = "Polygon PoS Chain",
        feeUsdt = 0.15,
        confirmationTime = "~1-2 menit",
        addressPrefixHint = "0x..."
    )
}

data class TransactionHistoryItem(
    val id: String,
    val title: String,
    val subtitle: String,
    val date: String,
    val amountRupiah: String,
    val amountCrypto: String? = null,
    val isPositive: Boolean = false,
    val category: String
)
