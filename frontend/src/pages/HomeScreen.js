import React from 'react';
import Background from '../components/common/LoginRegister/Background';
import Logo from '../components/common/LoginRegister/Logo';
import Header from '../components/common/LoginRegister/Header';
import Button from '../components/common/LoginRegister/Button';
import Paragraph from '../components/common/LoginRegister/Paragraph';
import LoginScreen from './LoginScreen';
import RegisterScreen from './RegisterScreen';

class HomeScreen extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            /*
            1 - home screen
            2 - login page
            3 - register page
            */
            step: 1,
        }
    }

    toHomePage = () => {
        console.log("to home");
        this.setState({step: 1});
    }

    toLoginPage = () => {
        console.log("to login");
        this.setState({step: 2});
    }

    toRegisterPage = () => {
        console.log("to register");
        this.setState({step: 3});
    }

    render() {
        if (this.state.step == 1) {
            return(
                <Background>
                    <Logo />
                    <Header>Planit</Header>
    
                    <Paragraph>
                    Welcome to Planit.
                    </Paragraph>
                    <Button mode="contained" onPress={this.toLoginPage}>
                    Login
                    </Button>
                    <Button
                    mode="outlined"
                    onPress={this.toRegisterPage}
                    >
                    Sign Up
                    </Button>
                </Background>
            );
        }
        else if (this.state.step == 2) {
            return(
                <LoginScreen back={this.toHomePage} register={this.toRegisterPage}/>
            );
        }
        else if (this.state.step == 3) {
            return (
                <RegisterScreen back={this.toHomePage} login={this.toLoginPage}/>
            );
        }
    }
}

// const HomeScreen = ({ navigation }) => (
//   <Background>
//     <Logo />
//     <Header>Login Template</Header>

//     <Paragraph>
//       The easiest way to start with your amazing application.
//     </Paragraph>
//     <Button mode="contained" onPress={() => navigation.navigate('LoginScreen')}>
//       Login
//     </Button>
//     <Button
//       mode="outlined"
//       onPress={() => navigation.navigate('RegisterScreen')}
//     >
//       Sign Up
//     </Button>
//   </Background>
// );

export default HomeScreen;
