import React from 'react';
import ImageCard from './ImageCard';
import TravelCard from './TravelCard';
import { StyleSheet, Text, View } from 'react-native';

var images = {
  toronto: require("../../images/toronto.jpg")
}

export default class EventCard extends React.Component{
  constructor(props){
    super(props);
  }

  render(){
    switch(this.props.common.type){
      case "transportation":
        return <TravelCard
          title={this.props.common.title}
          icon={this.props.common.icon}
          subtitle={this.props.common.subtitle}/>;
      case "event":
        return <ImageCard
          title={this.props.common.title}
          image={images[this.props.common.image]}
          text={this.props.common.description} />;
    }
  }
}
