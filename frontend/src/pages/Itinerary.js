import React from 'react';
import { StyleSheet, Text, View } from 'react-native';
import CardsContainer from './../sections/CardsContainer';

export default class Itinerary extends React.Component {

  const trip = {
    ItineraryArray: [{
    type: "event",
    title: "soccer",
    image="../images/toronto.jpg"
    description: "some text"
  },
  {
    type: "transportation",
    title: "car",
    subtitle: "30 minutes"
    icon: "flight-takeoff"
  },
  {
    type: "event",
    title: "soccer",
    image="../images/toronto.jpg"
    description: "some text"
  }]}
  render(){
    return(
      <View style={{flex: 1, justifyContent: 'center', alignItems: 'center'}}>
        <CardsContainer trip={ trip }/>
      </View>
    );
  }
}
