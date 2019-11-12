import React from 'react';
import { StyleSheet, Text, View } from 'react-native';
import CardsContainer from '../sections/CardsContainer';
import CreateItinerary from '../navigation/CreateItinerary';
import axios from 'axios';


export default class Itinerary extends React.Component {
  fetch(){
    axios.get("localhost/api/v1/getItinerary")
  }

  componentDidMount(){
    this.fetch();
  }

  render(){
    return(
      <View style={{flex: 1, justifyContent: 'center', alignItems: 'center'}}>
        <CardsContainer />
      </View>
    );
  }
}
