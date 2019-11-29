import React, { Component } from 'react';
import { Card, ListItem } from 'react-native-elements'

var travelDictionary = {
  Walk: "directions-walk",
  Bike: "directions-bike",
  Drive: "directions-car",
  Transit: "directions-transit"
}

class TravelCard extends Component{
  constructor(props){
    super(props)
  }

  render(){
    return (
      <Card><ListItem
        title={this.props.title}
        leftIcon={{ name: travelDictionary[this.props.title] }}
        subtitle={this.props.subtitle}
        chevron
        /></Card>
    );
  }
}

export default TravelCard;
