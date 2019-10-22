import React from 'react';
import { StyleSheet, Text, View } from 'react-native';
import CardsContainer from './../sections/CardsContainer';



export default class Itinerary extends React.Component {
  render(){
    return(
      <View style={{flex: 1, justifyContent: 'center', alignItems: 'center'}}>
        <CardsContainer />
      </View>
    );
  }
}
