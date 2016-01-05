package br.com.fut

import org.apache.commons.lang.builder.HashCodeBuilder

class UsuarioPapel implements Serializable {

	Usuario usuario
	Papel papel

	boolean equals(other) {
		if (!(other instanceof UsuarioPapel)) {
			return false
		}

		other.usuario?.id == usuario?.id &&
			other.papel?.id == papel?.id
	}

	int hashCode() {
		def builder = new HashCodeBuilder()
		if (usuario) builder.append(usuario.id)
		if (papel) builder.append(papel.id)
		builder.toHashCode()
	}

	static UsuarioPapel get(long usuarioId, long papelId) {
		find 'from UsuarioPapel where usuario.id=:usuarioId and papel.id=:papelId',
			[usuarioId: usuarioId, papelId: papelId]
	}

	static UsuarioPapel create(Usuario usuario, Papel papel, boolean flush = false) {
		new UsuarioPapel(usuario: usuario, papel: papel).save(flush: flush, insert: true)
	}

	static boolean remove(Usuario usuario, Papel papel, boolean flush = false) {
		UsuarioPapel instance = UsuarioPapel.findByUsuarioAndPapel(usuario, papel)
		if (!instance) {
			return false
		}

		instance.delete(flush: flush)
		true
	}

	static void removeAll(Usuario usuario) {
		executeUpdate 'DELETE FROM UsuarioPapel WHERE usuario=:usuario', [usuario: usuario]
	}

	static void removeAll(Papel papel) {
		executeUpdate 'DELETE FROM UsuarioPapel WHERE papel=:papel', [papel: papel]
	}

	static mapping = {
		id composite: ['papel', 'usuario']
		version false
	}
}
