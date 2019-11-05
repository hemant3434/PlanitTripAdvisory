import React from 'react';
import { StyleSheet, Text, View } from 'react-native';
import HybridApp from './src/App';
import HomeScreen from './src/navigation/HomeScreen';

export default class App extends React.Component {
  constructor(props) {
  super(props);
  this.state = {
    isReady: false
    };
  }

  componentWillMount() {
    this.loadFonts();
  }
  async loadFonts() {
    await Expo.Font.loadAsync({
      Roboto: require("./node_modules/native-base/Fonts/Roboto.ttf"),
      Roboto_medium: require("./node_modules/native-base/Fonts/Roboto_medium.ttf"),
    });
    this.setState({ isReady: true });
  }

  render(){
    if(!this.state.isReady){
      return (<View></View>);
    }
    return (
      <HomeScreen /> // should be HybridApp, but changed temp for login
    );
  }
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#fff',
    alignItems: 'center',
    justifyContent: 'center',
  },
});
