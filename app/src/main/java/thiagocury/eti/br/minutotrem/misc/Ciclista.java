package thiagocury.eti.br.minutotrem.misc;

/***
 * @author Leandro Soares Quevedo
 * @version 1.0
 * @since 11/09/16
 ***/
public class Ciclista {
	private String categoria;
	private String imagem;
	private String descricao;

	public Ciclista (String categoria, String imagem, String descricao) {
		this.categoria = categoria;
		this.imagem = imagem;
		this.descricao = descricao;
	}

	public Ciclista () {
	}

	public String getCategoria () {
		return categoria;
	}

	public void setCategoria (String categoria) {
		this.categoria = categoria;
	}

	public String getImagem () {
		return imagem;
	}

	public void setImagem (String imagem) {
		this.imagem = imagem;
	}

	public String getDescricao () {
		return descricao;
	}

	public void setDescricao (String descricao) {
		this.descricao = descricao;
	}

	@Override
	public String toString () {
		final StringBuffer sb = new StringBuffer ();
		sb.append ("categoria='").append (categoria).append ('\n');
		sb.append ("\nimagem='").append (imagem).append ('\n');
		sb.append ("\ndescricao='").append (descricao).append ('\n');
		return sb.toString ();
	}
}
