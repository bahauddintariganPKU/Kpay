package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.TransactionHistoryItem
import com.example.ui.components.BalanceWidget
import com.example.ui.components.MenuGrid
import com.example.ui.components.PpobModalSheet
import com.example.ui.components.PromoBanner
import com.example.ui.components.RecentTransactions
import com.example.ui.theme.BackgroundLight
import com.example.ui.theme.IceBlue
import com.example.ui.theme.PrimaryBlue
import com.example.ui.theme.PrimaryBlueDark
import com.example.ui.theme.PureWhite
import com.example.ui.theme.SuccessGreen
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TextSecondary
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(
    rupiahBalance: Long,
    usdtBalance: Double,
    transactionList: List<TransactionHistoryItem>,
    onBeliStablecoinClick: () -> Unit,
    onPpobPurchase: (String, Long) -> Unit,
    onTopUpRupiah: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    var activePpobModalTitle by remember { mutableStateOf<String?>(null) }
    val ppobSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        containerColor = BackgroundLight,
        modifier = modifier
            .fillMaxSize()
            .testTag("dashboard_screen")
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
        ) {
            // Modern Header Bar with User Profile & Status Badge
            Surface(
                color = PureWhite,
                shadowElevation = 1.dp,
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 14.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Surface(
                            shape = CircleShape,
                            color = PrimaryBlue,
                            modifier = Modifier.size(42.dp)
                        ) {
                            Box(contentAlignment = Alignment.Center) {
                                Text(
                                    text = "BP",
                                    color = PureWhite,
                                    fontSize = 15.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }

                        Column {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text(
                                    text = "Halo, Budi Pratama",
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = TextPrimary
                                )
                                Spacer(modifier = Modifier.width(6.dp))
                                Surface(
                                    shape = RoundedCornerShape(6.dp),
                                    color = SuccessGreen.copy(alpha = 0.15f)
                                ) {
                                    Text(
                                        text = "✓ KYC",
                                        color = SuccessGreen,
                                        fontSize = 10.sp,
                                        fontWeight = FontWeight.Bold,
                                        modifier = Modifier.padding(horizontal = 4.dp, vertical = 2.dp)
                                    )
                                }
                            }
                            Text(
                                text = "FinTech Crypto & PPOB ID: 882910",
                                fontSize = 11.sp,
                                color = TextSecondary
                            )
                        }
                    }

                    // Notification bell circle
                    Surface(
                        shape = CircleShape,
                        color = IceBlue,
                        modifier = Modifier.size(38.dp)
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            Text(
                                text = "🔔",
                                fontSize = 16.sp
                            )
                        }
                    }
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // 1. Widget Saldo Utama (Rupiah & USDT)
                BalanceWidget(
                    rupiahBalance = rupiahBalance,
                    usdtBalance = usdtBalance,
                    onTopUpClick = {
                        onTopUpRupiah(1_000_000L)
                        coroutineScope.launch {
                            snackbarHostState.showSnackbar("Top Up Berhasil! +Rp 1.000.000 ditambahkan.")
                        }
                    },
                    onWithdrawClick = {
                        coroutineScope.launch {
                            snackbarHostState.showSnackbar("Fitur Tarik Saldo ke Rekening Bank siap digunakan.")
                        }
                    },
                    onSendClick = {
                        coroutineScope.launch {
                            snackbarHostState.showSnackbar("Fitur Transfer Antar Dompet siap digunakan.")
                        }
                    },
                    onHistoryClick = {
                        coroutineScope.launch {
                            snackbarHostState.showSnackbar("Menampilkan riwayat transaksi lengkap.")
                        }
                    }
                )

                // 2. Grid Menu (Pulsa, Token PLN, Tagihan PPOB, Beli Stablecoin)
                MenuGrid(
                    onPulsaClick = { activePpobModalTitle = "Isi Pulsa & Kuota Seluler" },
                    onTokenPlnClick = { activePpobModalTitle = "Beli Token Listrik PLN" },
                    onTagihanPpobClick = { activePpobModalTitle = "Bayar Tagihan PPOB" },
                    onBeliStablecoinClick = onBeliStablecoinClick
                )

                // 3. Promotional Banner
                PromoBanner(
                    onBannerClick = {
                        coroutineScope.launch {
                            snackbarHostState.showSnackbar("Promo Cashback 10% aktif untuk pembelian berikutnya!")
                        }
                    }
                )

                // 4. Transaksi Terbaru
                RecentTransactions(
                    items = transactionList,
                    onItemClick = { item ->
                        coroutineScope.launch {
                            snackbarHostState.showSnackbar("Detail transaksi: ${item.title}")
                        }
                    }
                )

                Spacer(modifier = Modifier.height(84.dp))
            }
        }
    }

    // Modal Sheet for PPOB features
    if (activePpobModalTitle != null) {
        PpobModalSheet(
            featureTitle = activePpobModalTitle!!,
            sheetState = ppobSheetState,
            onDismiss = { activePpobModalTitle = null },
            onPaySuccess = { title, amount ->
                activePpobModalTitle = null
                onPpobPurchase(title, amount)
                coroutineScope.launch {
                    snackbarHostState.showSnackbar("Pembayaran $title senilai Rp %,d berhasil!".format(amount))
                }
            }
        )
    }
}

