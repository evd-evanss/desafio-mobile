package com.sugarspoon.desafiomobile.feature.home.data.response

import com.sugarspoon.desafiomobile.feature.home.domain.model.MovieItem
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieItemResponse(
    @SerialName("id") val id: String,
    @SerialName("title") val title: String,
    @SerialName("originalTitle") val originalTitle: String,
    @SerialName("type") val type: String,
    @SerialName("movieIdUrl") val movieIdUrl: String?,
    @SerialName("ancineId") val ancineId: String?,
    @SerialName("countryOrigin") val countryOrigin: String?,
    @SerialName("priority") val priority: Int,
    @SerialName("contentRating") val contentRating: String?,
    @SerialName("duration") val duration: String?,
    @SerialName("rating") val rating: Double,
    @SerialName("synopsis") val synopsis: String?,
    @SerialName("cast") val cast: String?,
    @SerialName("director") val director: String?,
    @SerialName("directors") val directors: String?,
    @SerialName("imageFeatured") val imageFeatured: String?,
    @SerialName("distributor") val distributor: String?,
    @SerialName("inPreSale") val inPreSale: Boolean,
    @SerialName("isReexhibition") val isReexhibition: Boolean,
    @SerialName("isComingSoon") val isComingSoon: Boolean,
    @SerialName("urlKey") val urlKey: String?,
    @SerialName("isPlaying") val isPlaying: Boolean,
    @SerialName("countIsPlaying") val countIsPlaying: Int,
    @SerialName("premiereDate") val premiereDate: PremiereDateResponse?,
    @SerialName("creationDate") val creationDate: String?,
    @SerialName("city") val city: String?,
    @SerialName("siteURL") val siteURL: String?,
    @SerialName("nationalSiteURL") val nationalSiteURL: String?,
    @SerialName("images") val images: List<MovieImageResponse>?,
    @SerialName("genres") val genres: List<String>?,
    @SerialName("ratingDescriptors") val ratingDescriptors: List<String>?,
    @SerialName("accessibilityHubs") val accessibilityHubs: List<String>?,
    @SerialName("completeTags") val completeTags: List<CompleteTagsResponse>?,
    @SerialName("tags") val tags: List<String>?,
    @SerialName("trailers") val trailers: List<TrailerResponse>?,
    @SerialName("partnershipType") val partnershipType: String?,
    @SerialName("titleSeen") val titleSeen: String?,
    @SerialName("ratingDetails") val ratingDetails: RatingDetailsResponse?,
    @SerialName("b2BEventId") val b2BEventId: String?,
    @SerialName("cities") val cities: List<String>?
)

@Serializable
data class CompleteTagsResponse(
    @SerialName("name") val name: String
)

fun MovieItemResponse.toDomain() = MovieItem(
    id = id,
    title = title,
    originalTitle = originalTitle,
    type = type,
    movieIdUrl = movieIdUrl ?: "",
    ancineId = ancineId ?: "",
    countryOrigin = countryOrigin ?: "",
    priority = priority,
    contentRating = contentRating ?: "",
    duration = duration ?: "",
    rating = rating,
    synopsis = synopsis ?: "",
    cast = cast ?: "",
    director = director ?: "",
    directors = directors ?: "",
    imageFeatured = imageFeatured ?: "",
    distributor = distributor ?: "",
    inPreSale = inPreSale,
    isReexhibition = isReexhibition,
    isComingSoon = isComingSoon,
    urlKey = urlKey ?: "",
    isPlaying = isPlaying,
    countIsPlaying = countIsPlaying,
    premiereDate = premiereDate?.toDomain(),
    creationDate = creationDate ?: "",
    city = city ?: "",
    siteURL = siteURL ?: "",
    nationalSiteURL = nationalSiteURL ?: "",
    images = images?.map { it.toDomain() } ?: emptyList(),
    genres = genres ?: emptyList(),
    ratingDescriptors = ratingDescriptors ?: emptyList(),
    accessibilityHubs = accessibilityHubs ?: emptyList(),
    completeTags = completeTags ?: emptyList(),
    tags = tags ?: emptyList(),
    trailers = trailers.toDomain(),
    partnershipType = partnershipType ?: "",
    titleSeen = titleSeen ?: "",
    ratingDetails = ratingDetails?.toDomain(),
    b2BEventId = b2BEventId ?: "",
    cities = cities ?: emptyList()
)