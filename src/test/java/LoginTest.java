import app.netlify.bugbank.pages.Cadastro;
import app.netlify.bugbank.pages.Login;
import org.junit.jupiter.api.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class LoginTest extends BaseTest {
    private Cadastro cadastro;
    private Login login;

    @BeforeEach
    public void setUp() {
        super.setUp();
        cadastro = new Cadastro(driver);
        login = new Login(driver);
    }


    @Test
    @Order(1)
    public void testLoginComSucesso() {
        cadastro.clicarBotaoRegistrar();
        cadastro.realizarCadastro("nometeste@gmail.com", "testeUsuario", "Senha123");
        cadastro.fecharModal();
        login.realizarLogin("nometeste@gmail.com", "Senha123");

    }

    @Test
    @Order(2)
    public void testLoginSemSucesso() {
        login.realizarLogin("nometeste@gmail.com", "senha123");
        login.fecharModal();
    }


}