package com.example.supperheroes.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.supperheroes.R

data class SuperHero (
    @DrawableRes val imageHero: Int,
    @StringRes val nameHero: Int,
    @StringRes val descriptionHero: Int,
)
val listHero= listOf(
    SuperHero(R.drawable.hero1,R.string.hero1,R.string.description1),
    SuperHero(R.drawable.hero2,R.string.hero2,R.string.description2),
    SuperHero(R.drawable.hero3,R.string.hero3,R.string.description3),
    SuperHero(R.drawable.hero4,R.string.hero4,R.string.description4),
    SuperHero(R.drawable.hero5,R.string.hero5,R.string.description5),
    SuperHero(R.drawable.hero6,R.string.hero6,R.string.description6),
    SuperHero(R.drawable.hero7,R.string.hero7,R.string.description7),
)