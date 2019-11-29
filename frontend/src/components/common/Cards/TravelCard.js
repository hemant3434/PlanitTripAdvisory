import React, { Component } from 'react';
import { Card, ListItem } from 'react-native-elements'

var travelDictionary = {
  walk: "directions-walk",
  bike: "directions-bike"
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
