enum class Nivel { BÁSICO, INTERMEDIÁRIO, AVANÇADO }

data class Usuario(var nome: String, var id: Int = 0)

class NomeDuplicadoException(var nome: String) : Exception("Usuário com nome '$nome' já cadastrado na formação.")

data class ConteudoEducacional(var nome: String, var duracao: Int = 60)

data class Formacao(val nome: String, var nivel: Nivel = Nivel.BÁSICO, var conteudos: List<ConteudoEducacional>) {
    private var contadorInscricoes = 20231
    
    val inscritos = mutableListOf<Usuario>()
    
    fun matricular(usuario: Usuario) {
		val usuarioExistente = inscritos.find { it.nome == usuario.nome}
		
		if (usuarioExistente != null) { 
			throw NomeDuplicadoException(usuario.nome)
		} else {
		    usuario.id = contadorInscricoes
			inscritos.add(usuario)
			contadorInscricoes++
		}
    }
}

fun main() {
    val conteudo1 = ConteudoEducacional("Introdução à Programação", 60)
    val conteudo2 = ConteudoEducacional("Estruturas de Dados", 90)
    
    val formacao1 = Formacao("Desenvolvedor Kotlin", Nivel.BÁSICO, listOf(conteudo1, conteudo2))
    val formacao2 = Formacao("Desenvolvedor Java", Nivel.INTERMEDIÁRIO, listOf(conteudo1, conteudo2))
    
    val usuario1 = Usuario("Josué C. Graham")
    val usuario2 = Usuario("Julia C. Lecker")
    
	val usuario3 = Usuario("Jocelina C. Bloom")
	
    formacao1.matricular(usuario1)
    formacao1.matricular(usuario2)
	
	formacao2.matricular(usuario3)
    
    println("Inscritos para a formação ${formacao1.nome} (nível ${formacao1.nivel}): \n${formacao1.inscritos.joinToString("\n") { "${it.id} - ${it.nome}" }}")
    println("\n")
    println("Conteúdo Educacional (${formacao1.nome}): \n${formacao1.conteudos.joinToString("\n") { "${it.nome} (${it.duracao}min)" }}")

	
	println("\n")
    println("Inscritos para a formação ${formacao2.nome} (nível ${formacao2.nivel}): \n${formacao2.inscritos.joinToString("\n") { "${it.id} - ${it.nome}" }}")
    println("\n")
    println("Conteúdo Educacional (${formacao2.nome}): \n${formacao2.conteudos.joinToString("\n") { "${it.nome} (${it.duracao}min)" }}")
}
