package com.laznas.mylmi.model

import com.google.gson.annotations.SerializedName

data class ResponseFundraisings(

	@field:SerializedName("ResponseFundraisings")
	val responseFundraisings: List<ResponseFundraisingsItem?>? = null
)

data class ResponseFundraisingsItem(

	@field:SerializedName("image")
	val image: String? = null,

	@field:SerializedName("village_id")
	val villageId: Any? = null,

	@field:SerializedName("collected_fund")
	val collectedFund: Int? = null,

	@field:SerializedName("regency_id")
	val regencyId: Int? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("created_at")
	val createdAt: String? = null,

	@field:SerializedName("target_fund")
	val targetFund: Int? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("type")
	val type: String? = null,

	@field:SerializedName("category_id")
	val categoryId: Int? = null,

	@field:SerializedName("updated_at")
	val updatedAt: String? = null,

	@field:SerializedName("user_id")
	val userId: Int? = null,

	@field:SerializedName("province_id")
	val provinceId: Int? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("district_id")
	val districtId: Any? = null,

	@field:SerializedName("deadline")
	val deadline: String? = null,

	@field:SerializedName("submission_status")
	val submissionStatus: String? = null,

	@field:SerializedName("slug")
	val slug: String? = null,

	@field:SerializedName("status")
	val status: String? = null
)
