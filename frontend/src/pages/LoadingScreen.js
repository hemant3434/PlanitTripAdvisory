import React from 'react';
import { StyleSheet, ActivityIndicator, View } from 'react-native';
import Logo from '../components/common/LoginRegister/Logo';
import Header from '../components/common/LoginRegister/Header';


export default class LoadingPage extends React.Component {
  render(){
    return(
    <View style={styles.container}>
        <View style={styles.horizontal}>
            <Logo />
        </View>
        <View style={styles.horizontal}>
            <Header>LOADING!</Header>
        </View>
        <View style={styles.horizontal}>
            <ActivityIndicator size="large" color='#600EE6'/>
        </View>
    </View>
    );
  }
}


const styles = StyleSheet.create({
    container: {
      flex: 1,
      justifyContent: 'center'
    },
    horizontal: {
      flexDirection: 'row',
      justifyContent: 'space-around',
      padding: 10
    }
  })