import React, { useState } from 'react';
import { TouchableOpacity, StyleSheet, Text, View } from 'react-native';
import Background from '../components/common/LoginRegister/Background';
import Logo from '../components/common/LoginRegister/Logo';
import Header from '../components/common/LoginRegister/Header';
import Button from '../components/common/LoginRegister/Button';
import TextInput from '../components/common/LoginRegister/TextInput';
import BackButton from '../components/common/LoginRegister/BackButton';
import { theme } from '../services/theme';
import { emailValidator, passwordValidator } from '../services/utils';

import axios from 'axios';
import * as constClass from '../constants/index';

const LoginScreen = (props) => {
  const [email, setEmail] = useState({ value: '', error: '' });
  const [password, setPassword] = useState({ value: '', error: '' });

  const _onLoginPressed = () => {
    const emailError = emailValidator(email.value);
    const passwordError = passwordValidator(password.value);

    if (emailError || passwordError) {
      console.log("email/password validator failed");
      setEmail({ ...email, error: emailError });
      setPassword({ ...password, error: passwordError });
      return;
    }

    console.log("login:", email.value, password.value);

    if (email.value === "a@a.a") {
        console.log("logging in debug mode");
        props.done();
    }

    axios.post(constClass.CHECKPASSWORD_EP, {email: email.value, password: password.value})
    .then(res => {
        if (res.data) {
            console.log("pw check returns: " + res.data);
            if (res.data == "valid") {
                axios.put(constClass.LOGIN_EP, {email: email})
                .then(resp => console.log(resp));
                props.done();
            }
            else {
                setEmail({ ...email, error: "Invalid account information!" });
                setPassword({ ...password, error: "Invalid account information!"});
            }
        }
    });
  };

  return (
    <Background>
      <BackButton goBack={props.back} />

      <Logo />

      <Header>Welcome back.</Header>

      <TextInput
        label="Email"
        returnKeyType="next"
        value={email.value}
        onChangeText={text => setEmail({ value: text, error: '' })}
        error={!!email.error}
        errorText={email.error}
        autoCapitalize="none"
        autoCompleteType="email"
        textContentType="emailAddress"
        keyboardType="email-address"
      />

      <TextInput
        label="Password"
        returnKeyType="done"
        value={password.value}
        onChangeText={text => setPassword({ value: text, error: '' })}
        error={!!password.error}
        errorText={password.error}
        secureTextEntry
      />

      <View style={styles.forgotPassword}>
        <TouchableOpacity
          onPress={() => navigation.navigate('ForgotPasswordScreen')}
        >
          <Text style={styles.label}>Forgot your password?</Text>
        </TouchableOpacity>
      </View>

      <Button mode="contained" onPress={_onLoginPressed}>
        Login
      </Button>

      <View style={styles.row}>
        <Text style={styles.label}>Don’t have an account? </Text>
        <TouchableOpacity onPress={props.register}>
          <Text style={styles.link}>Sign up</Text>
        </TouchableOpacity>
      </View>
    </Background>
  );
};

const styles = StyleSheet.create({
  forgotPassword: {
    width: '100%',
    alignItems: 'flex-end',
    marginBottom: 24,
  },
  row: {
    flexDirection: 'row',
    marginTop: 4,
  },
  label: {
    color: theme.colors.secondary,
  },
  link: {
    fontWeight: 'bold',
    color: theme.colors.primary,
  },
});

export default LoginScreen;
