import React from 'react';
import { StyleSheet, Text, View } from 'react-native';
import HybridApp from './src/App';

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
      <HybridApp />
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
