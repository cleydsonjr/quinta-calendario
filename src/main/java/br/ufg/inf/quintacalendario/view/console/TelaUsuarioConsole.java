package br.ufg.inf.quintacalendario.view.console;

import br.ufg.inf.quintacalendario.controller.Usuario;
import br.ufg.inf.quintacalendario.view.TelaInicial;
import br.ufg.inf.quintacalendario.view.console.util.EntradaConsole;

import java.io.PrintStream;


public class TelaUsuarioConsole extends AbstractTelaCabecalho implements TelaInicial {

    private EntradaConsole entradaConsole;

    public TelaUsuarioConsole(PrintStream output) {
        super(output);
        setEntradaConsole(new EntradaConsole());
    }

    @Override
    public void exibaOpcoes() {
        exibaCabecalho();
        desenhaOpcoes();
        realizarLogin();
    }

    private void redirect(Integer opcao) {
        switch (opcao) {
            case 1:
                //Listar usuários cadastrados    

                break;
            case 2:
                //Cadastrar um novo usuário   
                break;
            case 3:
                //Remover usuário    
                break;
            case 4:
                //Alterar dados do usuário    

                break;
            default:

                break;
        }
    }

    @Override
    public int pergunteOpcao() {
        return 0;
    }

    public EntradaConsole getEntradaConsole() {
        return entradaConsole;
    }

    public void setEntradaConsole(EntradaConsole entradaConsole) {
        this.entradaConsole = entradaConsole;
    }

    public String desenhaOpcoes() {
        StringBuilder tela = new StringBuilder();
        tela.append("1 - Listar usuários cadastrados \n")
                .append("2 - Cadastar um novo usuário \n")
                .append("3 - Remover usuário \n")
                .append("4 - Alterar dados de usuário");
        return tela.toString();
    }

    /**
     * Método que pergunta o login e a senha do usuário e retorna um objeto
     * com os dados que foram inseridos.
     *
     * @return Objeto usuário com o nome que corresponde ao login e a senha.
     */
    public Usuario realizarLogin() {
        exibaCabecalho();
        Usuario usuarioLogin = new Usuario();
        usuarioLogin.setNome(new EntradaConsole().pergunteString("- Login: \n"));
        usuarioLogin.setSenha(new EntradaConsole().pergunteString("- Senha: "));
        System.out.println("Opcao em desenvolvimento");
        return usuarioLogin;
    }

    public Usuario cadastrarUsuario() {
        Usuario novoUsuario = new Usuario();
        novoUsuario.setNome(new EntradaConsole().pergunteString("Novo Login: "));
        novoUsuario.setSenha(new EntradaConsole().pergunteString("Nova Senha: "));
        String confirmaSenha = new EntradaConsole().pergunteString("Confirme a senha: ");

        if (novoUsuario.getSenha().equals(confirmaSenha)) {
            //Salvar no banco
        } else {
            System.out.println("As senhas não são iguais!\n");
            cadastrarUsuario();
        }
        return novoUsuario;
    }


}
