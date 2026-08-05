package com.vpn.fovix.app.presentation.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.vpn.fovix.domain.subscription.VpnSubscription



@Composable
fun SubscriptionCard(

    subscription: VpnSubscription?,

    onAddClick: () -> Unit

) {

    Column(

        modifier = Modifier
            .fillMaxWidth()
            .shadow(
                elevation = 8.dp,
                shape = RoundedCornerShape(24.dp)
            )
            .background(
                Color.White,
                RoundedCornerShape(24.dp)
            )
            .border(
                1.dp,
                Color(0xFFE7ECF2),
                RoundedCornerShape(24.dp)
            )
            .padding(20.dp)

    ) {

        Row(

            modifier = Modifier.fillMaxWidth(),

            horizontalArrangement = Arrangement.SpaceBetween,

            verticalAlignment = Alignment.CenterVertically

        ) {

            Column {

                Text(

                    text = "VPN Provider",

                    fontSize = 12.sp,

                    color = Color(0xFF94A3B8)

                )

                Spacer(
                    modifier = Modifier.height(6.dp)
                )

                Text(

                    text = subscription?.name ?: "No subscription",

                    fontSize = 20.sp,

                    fontWeight = FontWeight.SemiBold,

                    color = Color(0xFF111827)

                )

            }

            IconButton(

                onClick = onAddClick

            ) {

                Icon(

                    imageVector = Icons.Rounded.Add,

                    contentDescription = "Add subscription",

                    tint = Color(0xFF0284C7)

                )

            }

        }

        Spacer(
            modifier = Modifier.height(20.dp)
        )

        if (subscription == null) {

            Text(

                text = "Import a VPN provider subscription to start using servers.",

                color = Color(0xFF64748B),

                fontSize = 14.sp

            )

        } else {

            Row(

                modifier = Modifier.fillMaxWidth(),

                horizontalArrangement = Arrangement.SpaceBetween

            ) {

                Column {

                    Text(

                        text = "Servers",

                        color = Color(0xFF94A3B8),

                        fontSize = 11.sp

                    )

                    Spacer(
                        modifier = Modifier.height(4.dp)
                    )

                    Text(

                        text = subscription.servers.size.toString(),

                        fontSize = 15.sp,

                        fontWeight = FontWeight.Medium,

                        color = Color(0xFF111827)

                    )

                }

                Column(

                    horizontalAlignment = Alignment.End

                ) {

                    Text(

                        text = "Status",

                        color = Color(0xFF94A3B8),

                        fontSize = 11.sp

                    )

                    Spacer(
                        modifier = Modifier.height(4.dp)
                    )

                    Text(

                        text =
                        if (subscription.isActive)
                            "ACTIVE"
                        else
                            "DISABLED",

                        fontSize = 15.sp,

                        fontWeight = FontWeight.Medium,

                        color =
                        if (subscription.isActive)
                            Color(0xFF16A34A)
                        else
                            Color(0xFFDC2626)

                    )

                }

            }

        }

    }

}