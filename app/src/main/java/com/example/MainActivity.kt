package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.model.CryptoNetwork
import com.example.model.TransactionHistoryItem
import com.example.ui.components.AppBottomNavigationBar
import com.example.ui.components.BottomNavTab
import com.example.ui.components.QrisModalSheet
import com.example.ui.screens.BuyStablecoinScreen
import com.example.ui.screens.DashboardScreen
import com.example.ui.screens.DompetScreen
import com.example.ui.screens.SayaScreen
import com.example.ui.screens.TransaksiScreen
import com.example.ui.theme.BackgroundLight
import com.example.ui.theme.MyApplicationTheme
import kotlinx.coroutines.launch

enum class AppDestination {
    MAIN_FLOW,
    BUY_STABLECOIN
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                MainApp()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainApp() {
    var currentDestination by remember { mutableStateOf(AppDestination.MAIN_FLOW) }
    var selectedTab by remember { mutableStateOf(BottomNavTab.BERANDA) }

    var rupiahBalance by remember { mutableLongStateOf(15_850_000L) }
    var usdtBalance by remember { mutableDoubleStateOf(1_450.50) }

    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    var showQrisSheet by remember { mutableStateOf(false) }
    val qrisSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    var transactions by remember {
        mutableStateOf(
            listOf(
                TransactionHistoryItem(
                    id = "TX-01",
                    title = "Beli 50 USDT (TRC-20)",
                    subtitle = "Beli Stablecoin",
                    date = "Hari ini, 10:45",
                    amountRupiah = "Rp 812.500",
                    amountCrypto = "+50.00 USDT",
                    isPositive = false,
                    category = "CRYPTO"
                ),
                TransactionHistoryItem(
                    id = "TX-02",
                    title = "Token Listrik PLN 100k",
                    subtitle = "ID: 14285719283",
                    date = "Kemarin, 19:20",
                    amountRupiah = "Rp 101.500",
                    isPositive = false,
                    category = "PLN"
                ),
                TransactionHistoryItem(
                    id = "TX-03",
                    title = "Pulsa Telkomsel 50k",
                    subtitle = "081234567890",
                    date = "09 Okt 2026",
                    amountRupiah = "Rp 51.500",
                    isPositive = false,
                    category = "PULSA"
                ),
                TransactionHistoryItem(
                    id = "TX-04",
                    title = "Top Up Saldo Bank BCA",
                    subtitle = "Virtual Account",
                    date = "08 Okt 2026",
                    amountRupiah = "Rp 2.000.000",
                    isPositive = true,
                    category = "TOPUP"
                )
            )
        )
    }

    AnimatedContent(
        targetState = currentDestination,
        transitionSpec = { fadeIn() togetherWith fadeOut() },
        label = "AppDestinationTransition"
    ) { destination ->
        when (destination) {
            AppDestination.MAIN_FLOW -> {
                Scaffold(
                    snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
                    containerColor = BackgroundLight,
                    bottomBar = {
                        AppBottomNavigationBar(
                            selectedTab = selectedTab,
                            onTabSelected = { tab ->
                                if (tab == BottomNavTab.QRIS) {
                                    showQrisSheet = true
                                } else {
                                    selectedTab = tab
                                }
                            }
                        )
                    }
                ) { innerPadding ->
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(bottom = innerPadding.calculateBottomPadding())
                    ) {
                        when (selectedTab) {
                            BottomNavTab.BERANDA -> {
                                DashboardScreen(
                                    rupiahBalance = rupiahBalance,
                                    usdtBalance = usdtBalance,
                                    transactionList = transactions,
                                    onBeliStablecoinClick = {
                                        currentDestination = AppDestination.BUY_STABLECOIN
                                    },
                                    onPpobPurchase = { title, amount ->
                                        if (rupiahBalance >= amount) {
                                            rupiahBalance -= amount
                                            val newTx = TransactionHistoryItem(
                                                id = "TX-${System.currentTimeMillis() % 10000}",
                                                title = title,
                                                subtitle = "Pembayaran PPOB",
                                                date = "Baru saja",
                                                amountRupiah = "Rp " + "%,d".format(amount).replace(',', '.'),
                                                isPositive = false,
                                                category = if (title.contains("PLN")) "PLN" else "PULSA"
                                            )
                                            transactions = listOf(newTx) + transactions
                                        }
                                    },
                                    onTopUpRupiah = { topUpAmount ->
                                        rupiahBalance += topUpAmount
                                        val newTx = TransactionHistoryItem(
                                            id = "TX-${System.currentTimeMillis() % 10000}",
                                            title = "Top Up Saldo",
                                            subtitle = "Instan Top Up",
                                            date = "Baru saja",
                                            amountRupiah = "Rp " + "%,d".format(topUpAmount).replace(',', '.'),
                                            isPositive = true,
                                            category = "TOPUP"
                                        )
                                        transactions = listOf(newTx) + transactions
                                    }
                                )
                            }

                            BottomNavTab.DOMPET -> {
                                DompetScreen(
                                    rupiahBalance = rupiahBalance,
                                    usdtBalance = usdtBalance,
                                    onTopUpClick = {
                                        rupiahBalance += 500_000L
                                        val newTx = TransactionHistoryItem(
                                            id = "TX-${System.currentTimeMillis() % 10000}",
                                            title = "Top Up Saldo",
                                            subtitle = "Instan Top Up",
                                            date = "Baru saja",
                                            amountRupiah = "Rp 500.000",
                                            isPositive = true,
                                            category = "TOPUP"
                                        )
                                        transactions = listOf(newTx) + transactions
                                        coroutineScope.launch {
                                            snackbarHostState.showSnackbar("Top Up Berhasil! +Rp 500.000 ditambahkan.")
                                        }
                                    },
                                    onBeliCryptoClick = {
                                        currentDestination = AppDestination.BUY_STABLECOIN
                                    }
                                )
                            }

                            BottomNavTab.QRIS -> {
                                // QRIS is also controlled via modal sheet
                                DashboardScreen(
                                    rupiahBalance = rupiahBalance,
                                    usdtBalance = usdtBalance,
                                    transactionList = transactions,
                                    onBeliStablecoinClick = {
                                        currentDestination = AppDestination.BUY_STABLECOIN
                                    },
                                    onPpobPurchase = { _, _ -> },
                                    onTopUpRupiah = { _ -> }
                                )
                            }

                            BottomNavTab.TRANSAKSI -> {
                                TransaksiScreen(
                                    transactions = transactions,
                                    onItemClick = { item ->
                                        coroutineScope.launch {
                                            snackbarHostState.showSnackbar("Detail transaksi: ${item.title}")
                                        }
                                    }
                                )
                            }

                            BottomNavTab.SAYA -> {
                                SayaScreen(
                                    onLogoutClick = {
                                        coroutineScope.launch {
                                            snackbarHostState.showSnackbar("Sesi akun aktif.")
                                        }
                                    }
                                )
                            }
                        }
                    }
                }
            }

            AppDestination.BUY_STABLECOIN -> {
                BuyStablecoinScreen(
                    currentRupiahBalance = rupiahBalance,
                    onBackClick = {
                        currentDestination = AppDestination.MAIN_FLOW
                    },
                    onPurchaseSuccess = { amountRupiah, amountUsdt, network, walletAddress ->
                        rupiahBalance -= amountRupiah
                        usdtBalance += amountUsdt
                        val newTx = TransactionHistoryItem(
                            id = "TX-${System.currentTimeMillis() % 10000}",
                            title = "Beli %.2f USDT (%s)".format(amountUsdt, network.displayName),
                            subtitle = "Ke: ${walletAddress.take(6)}...${walletAddress.takeLast(4)}",
                            date = "Baru saja",
                            amountRupiah = "Rp " + "%,d".format(amountRupiah).replace(',', '.'),
                            amountCrypto = "+%.2f USDT".format(amountUsdt),
                            isPositive = false,
                            category = "CRYPTO"
                        )
                        transactions = listOf(newTx) + transactions
                    }
                )
            }
        }
    }

    // Modal Sheet for QRIS
    if (showQrisSheet) {
        QrisModalSheet(
            sheetState = qrisSheetState,
            onDismiss = { showQrisSheet = false },
            onPaymentSuccess = { amount, merchantName ->
                showQrisSheet = false
                if (rupiahBalance >= amount) {
                    rupiahBalance -= amount
                    val newTx = TransactionHistoryItem(
                        id = "TX-${System.currentTimeMillis() % 10000}",
                        title = "QRIS - $merchantName",
                        subtitle = "Pembayaran QRIS",
                        date = "Baru saja",
                        amountRupiah = "Rp " + "%,d".format(amount).replace(',', '.'),
                        isPositive = false,
                        category = "PPOB"
                    )
                    transactions = listOf(newTx) + transactions
                    coroutineScope.launch {
                        snackbarHostState.showSnackbar("Pembayaran QRIS ke $merchantName sebesar Rp %,d berhasil!".format(amount))
                    }
                } else {
                    coroutineScope.launch {
                        snackbarHostState.showSnackbar("Saldo tidak mencukupi untuk pembayaran QRIS.")
                    }
                }
            }
        )
    }
}
