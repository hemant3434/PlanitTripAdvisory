import React, { Component } from 'react';
import { Card, ListItem } from 'react-native-elements'

class TravelCard extends Component{
  constructor(props){
    super(props)
  }

  render(props){
    return (
      <Card><ListItem
        title={this.props.title}
        leftIcon={{ name: this.props.icon }}
        subtitle={this.props.subtitle}
        chevron
        /></Card>
    );
  }
}

export default TravelCard;
