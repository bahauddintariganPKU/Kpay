package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.ui.theme.CryptoTeal
import com.example.ui.theme.IceBlueCard
import com.example.ui.theme.PrimaryBlue
import com.example.ui.theme.PrimaryBlueDark
import com.example.ui.theme.PureWhite
import com.example.ui.theme.SecondaryBlue
import com.example.ui.theme.SoftBlueBorder
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TextSecondary

@Composable
fun MenuGrid(
    onPulsaClick: () -> Unit,
    onTokenPlnClick: () -> Unit,
    onTagihanPpobClick: () -> Unit,
    onBeliStablecoinClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .testTag("menu_grid_card"),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = PureWhite),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Layanan Utama",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextPrimary
                )
                Text(
                    text = "PPOB & Crypto",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = PrimaryBlue
                )
            }

            Spacer(modifier = Modifier.height(14.dp))

            // 4 Items arranged in a 2x2 Clean Grid
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                // Item 1: Pulsa
                MenuItemCard(
                    title = stringResource(R.string.menu_pulsa),
                    subtitle = "Data & Seluler",
                    icon = AppIcons.Phone,
                    iconBgColor = Color(0xFF0284C7),
                    isHighlighted = false,
                    testTag = "menu_pulsa_button",
                    onClick = onPulsaClick,
                    modifier = Modifier.weight(1f)
                )

                // Item 2: Token PLN
                MenuItemCard(
                    title = stringResource(R.string.menu_token_pln),
                    subtitle = "Prabayar & Pasca",
                    icon = AppIcons.Bolt,
                    iconBgColor = Color(0xFFEAB308),
                    isHighlighted = false,
                    testTag = "menu_token_pln_button",
                    onClick = onTokenPlnClick,
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                // Item 3: Tagihan PPOB
                MenuItemCard(
                    title = stringResource(R.string.menu_tagihan),
                    subtitle = "BPJS, PDAM, WiFi",
                    icon = AppIcons.Receipt,
                    iconBgColor = Color(0xFF6366F1),
                    isHighlighted = false,
                    testTag = "menu_tagihan_button",
                    onClick = onTagihanPpobClick,
                    modifier = Modifier.weight(1f)
                )

                // Item 4: Beli Stablecoin (Prominent & Special Badge)
                MenuItemCard(
                    title = stringResource(R.string.menu_beli_crypto),
                    subtitle = "USDT / Multi-Chain",
                    icon = AppIcons.Crypto,
                    iconBgColor = PrimaryBlue,
                    isHighlighted = true,
                    highlightBadge = "CRYPTO",
                    testTag = "menu_beli_crypto_button",
                    onClick = onBeliStablecoinClick,
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}

@Composable
private fun MenuItemCard(
    title: String,
    subtitle: String,
    icon: ImageVector,
    iconBgColor: Color,
    isHighlighted: Boolean,
    testTag: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    highlightBadge: String? = null
) {
    Surface(
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .clickable(onClick = onClick)
            .testTag(testTag)
            .border(
                width = if (isHighlighted) 1.5.dp else 1.dp,
                color = if (isHighlighted) PrimaryBlue else SoftBlueBorder,
                shape = RoundedCornerShape(16.dp)
            ),
        shape = RoundedCornerShape(16.dp),
        color = if (isHighlighted) IceBlueCard else PureWhite
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Top
                ) {
                    // Icon Circle
                    Surface(
                        shape = CircleShape,
                        color = iconBgColor,
                        shadowElevation = 2.dp,
                        modifier = Modifier.size(40.dp)
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            Icon(
                                imageVector = icon,
                                contentDescription = title,
                                tint = PureWhite,
                                modifier = Modifier.size(22.dp)
                            )
                        }
                    }

                    // Optional Badge (e.g. CRYPTO / HOT)
                    if (highlightBadge != null) {
                        Surface(
                            shape = RoundedCornerShape(6.dp),
                            color = PrimaryBlue,
                            modifier = Modifier.padding(start = 4.dp)
                        ) {
                            Text(
                                text = highlightBadge,
                                color = PureWhite,
                                fontSize = 9.sp,
                                fontWeight = FontWeight.ExtraBold,
                                modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = title,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    color = if (isHighlighted) PrimaryBlueDark else TextPrimary,
                    maxLines = 1
                )

                Spacer(modifier = Modifier.height(2.dp))

                Text(
                    text = subtitle,
                    fontSize = 10.sp,
                    color = TextSecondary,
                    maxLines = 1
                )
            }
        }
    }
}
