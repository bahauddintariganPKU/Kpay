package com.example.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.IceBlue
import com.example.ui.theme.PrimaryBlue
import com.example.ui.theme.PrimaryBlueDark
import com.example.ui.theme.PureWhite
import com.example.ui.theme.SoftBlueBorder
import com.example.ui.theme.TextSecondary

enum class BottomNavTab {
    BERANDA,
    DOMPET,
    QRIS,
    TRANSAKSI,
    SAYA
}

@Composable
fun AppBottomNavigationBar(
    selectedTab: BottomNavTab,
    onTabSelected: (BottomNavTab) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .testTag("app_bottom_navigation_bar"),
        contentAlignment = Alignment.BottomCenter
    ) {
        // Base Navigation Bar Surface
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .shadow(
                    elevation = 12.dp,
                    spotColor = Color(0x330052FF),
                    ambientColor = Color(0x1A000000)
                ),
            color = PureWhite,
            shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .navigationBarsPadding()
            ) {
                // Top subtle divider/accent
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                        .background(SoftBlueBorder.copy(alpha = 0.5f))
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(64.dp)
                        .padding(horizontal = 4.dp),
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // 1. Beranda
                    BottomNavItem(
                        label = "Beranda",
                        icon = AppIcons.NavHome,
                        isSelected = selectedTab == BottomNavTab.BERANDA,
                        testTag = "bottom_nav_beranda",
                        onClick = { onTabSelected(BottomNavTab.BERANDA) },
                        modifier = Modifier.weight(1f)
                    )

                    // 2. Dompet
                    BottomNavItem(
                        label = "Dompet",
                        icon = AppIcons.NavWallet,
                        isSelected = selectedTab == BottomNavTab.DOMPET,
                        testTag = "bottom_nav_dompet",
                        onClick = { onTabSelected(BottomNavTab.DOMPET) },
                        modifier = Modifier.weight(1f)
                    )

                    // 3. Center Space for Elevated QRIS Button
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .height(64.dp),
                        contentAlignment = Alignment.BottomCenter
                    ) {
                        Text(
                            text = "QRIS",
                            fontSize = 11.sp,
                            fontWeight = if (selectedTab == BottomNavTab.QRIS) FontWeight.Bold else FontWeight.Medium,
                            color = if (selectedTab == BottomNavTab.QRIS) PrimaryBlue else TextSecondary,
                            modifier = Modifier.padding(bottom = 6.dp)
                        )
                    }

                    // 4. Transaksi
                    BottomNavItem(
                        label = "Transaksi",
                        icon = AppIcons.NavTransactions,
                        isSelected = selectedTab == BottomNavTab.TRANSAKSI,
                        testTag = "bottom_nav_transaksi",
                        onClick = { onTabSelected(BottomNavTab.TRANSAKSI) },
                        modifier = Modifier.weight(1f)
                    )

                    // 5. Saya
                    BottomNavItem(
                        label = "Saya",
                        icon = AppIcons.NavProfile,
                        isSelected = selectedTab == BottomNavTab.SAYA,
                        testTag = "bottom_nav_saya",
                        onClick = { onTabSelected(BottomNavTab.SAYA) },
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        }

        // Elevated Center QRIS Button protruding over the top edge
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .navigationBarsPadding()
                .offset(y = (-18).dp)
                .testTag("bottom_nav_qris_center_button")
        ) {
            val isQrisSelected = selectedTab == BottomNavTab.QRIS
            val scale by animateFloatAsState(
                targetValue = if (isQrisSelected) 1.08f else 1.0f,
                label = "qris_button_scale"
            )

            Surface(
                shape = CircleShape,
                color = PureWhite,
                shadowElevation = 8.dp,
                modifier = Modifier
                    .size(58.dp)
                    .scale(scale)
            ) {
                Box(
                    modifier = Modifier
                        .padding(3.dp)
                        .clip(CircleShape)
                        .background(
                            brush = Brush.verticalGradient(
                                colors = listOf(
                                    PrimaryBlue,
                                    PrimaryBlueDark
                                )
                            )
                        )
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = ripple(bounded = true, color = PureWhite),
                            onClick = { onTabSelected(BottomNavTab.QRIS) }
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = AppIcons.NavQris,
                        contentDescription = "Scan QRIS",
                        tint = PureWhite,
                        modifier = Modifier.size(28.dp)
                    )
                }
            }
        }
    }
}

@Composable
private fun BottomNavItem(
    label: String,
    icon: ImageVector,
    isSelected: Boolean,
    testTag: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val activeColor by animateColorAsState(
        targetValue = if (isSelected) PrimaryBlue else TextSecondary,
        label = "nav_item_color"
    )

    Column(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = ripple(bounded = true),
                onClick = onClick
            )
            .padding(vertical = 6.dp)
            .testTag(testTag),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            imageVector = icon,
            contentDescription = label,
            tint = activeColor,
            modifier = Modifier.size(23.dp)
        )

        Spacer(modifier = Modifier.height(3.dp))

        Text(
            text = label,
            fontSize = 11.sp,
            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
            color = activeColor
        )
    }
}
