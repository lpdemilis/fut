package br.com.fut



class FacebookUser {

	long uid
	String accessToken
	Date accessTokenExpires

	static belongsTo = [user: Usuario]

	static constraints = { uid unique: true }
}
