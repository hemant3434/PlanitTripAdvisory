import React from 'react';
import { StyleSheet, Text, View } from 'react-native';
import MapPicker from './components/MapPicker';
import CardsContainer from './sections/CardsContainer';
import Calendar from './components/Calendar';
import BottomNav from './components/Navigation/BottomNav';


export default class HybridApp extends React.Component {

  render(){
    return (
      <View style={styles.container}>
        <Text>PLAN YO SHIT</Text>
        <CardsContainer />
      </View>
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
