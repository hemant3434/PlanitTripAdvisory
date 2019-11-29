import React from 'react';
import { StyleSheet, View, ScrollView  } from 'react-native';
import {
  Text,
} from "native-base";
import { Card, Button, Icon } from 'react-native-elements'
import EventCard from './../components/common/Cards/EventCard';
import MapRoutePage from '../pages/MapRoutePage';

export default class CardsContainer extends React.Component{

  constructor(props){
    super(props);
    this.state = {
      isLoading: this.props.isLoading,
      Itinerary: this.props.common,
      cost: 0,
      showMap: false
    }
    this.previous = this.previous.bind(this)
    this.getMap = this.getMap.bind(this);
    this.props.common.forEach(Event => {
      if(Event.type === "event"){
        this.state = {
          ...this.state,
          cost: this.state.cost + Event.price * 10
        }
      }
    })
    this.updateCard = this.updateCard.bind(this);
  }

  getMap(){
    this.setState({
      showMap: true
    });
  }

  previous(){
    console.log("GOT TO PREVIOUS");
    this.setState({
      showMap: false
    });
  }

  updateCard(){
    this.props.update();
    this.forceUpdate();
  }

  render(){
      switch(this.state.showMap){
        case true:
          return (
            <MapRoutePage common={this.props.common} previous={this.previous}/>
          );
        case false:
            return (
            <ScrollView
            style={StyleSheet.absoluteFill}
            contentContainerStyle={styles.scrollview}>
            <Button style={{paddingTop: 50}}title='MAP' onPress={this.getMap}/>
            { !this.state.isLoading ? this.props.common.map(o => <EventCard common={o} update={this.updateCard}/>):<Text>Loading</Text> }
            <Card>
            <Text>Estimated Total Cost: ${this.state.cost}</Text>
            </Card>
            </ScrollView>
          );
      }
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
