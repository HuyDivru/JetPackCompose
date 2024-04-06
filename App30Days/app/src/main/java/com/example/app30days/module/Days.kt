package com.example.app30days.module

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.app30days.R

data class Days(
    @StringRes val nameDay:Int,
    @StringRes val titleDay:Int,
    @DrawableRes val imageDay:Int,
    @StringRes val descriptionDay:Int,
)
val listDay= listOf(
    Days(R.string.day1,R.string.title1,R.drawable.day1,R.string.description1),
    Days(R.string.day2,R.string.title2,R.drawable.day2,R.string.description2),
    Days(R.string.day3,R.string.title3,R.drawable.day3,R.string.description3),
    Days(R.string.day4,R.string.title4,R.drawable.day4,R.string.description4),
    Days(R.string.day5,R.string.title5,R.drawable.day5,R.string.description5),
    Days(R.string.day6,R.string.title6,R.drawable.day6,R.string.description6),
    Days(R.string.day7,R.string.title7,R.drawable.day7,R.string.description7),
    Days(R.string.day8,R.string.title8,R.drawable.day8,R.string.description8),
    Days(R.string.day9,R.string.title9,R.drawable.day9,R.string.description9),
    Days(R.string.day10,R.string.title10,R.drawable.day10,R.string.description10),
    Days(R.string.day11,R.string.title11,R.drawable.day11,R.string.description11),
    Days(R.string.day12,R.string.title12,R.drawable.day12,R.string.description12),
    Days(R.string.day13,R.string.title13,R.drawable.day13,R.string.description13),
    Days(R.string.day14,R.string.title14,R.drawable.day14,R.string.description14),
    Days(R.string.day15,R.string.title15,R.drawable.day15,R.string.description15),
    Days(R.string.day16,R.string.title16,R.drawable.day16,R.string.description16),
    Days(R.string.day17,R.string.title17,R.drawable.day17,R.string.description17),
    Days(R.string.day18,R.string.title18,R.drawable.day18,R.string.description18),
    Days(R.string.day19,R.string.title19,R.drawable.day19,R.string.description19),
    Days(R.string.day20,R.string.title20,R.drawable.day20,R.string.description20),
    Days(R.string.day21,R.string.title21,R.drawable.day21,R.string.description21),

    Days(R.string.day22,R.string.title22,R.drawable.day22,R.string.description22),
    Days(R.string.day23,R.string.title23,R.drawable.day23,R.string.description23),
    Days(R.string.day24,R.string.title24,R.drawable.day24,R.string.description24),
    Days(R.string.day25,R.string.title25,R.drawable.day25,R.string.description25),
    Days(R.string.day26,R.string.title26,R.drawable.day26,R.string.description26),
    Days(R.string.day27,R.string.title27,R.drawable.day27,R.string.description27),
    Days(R.string.day28,R.string.title28,R.drawable.day28,R.string.description28),
    Days(R.string.day29,R.string.title29,R.drawable.day29,R.string.description29),
    Days(R.string.day30,R.string.title30,R.drawable.day30,R.string.description30),
    )