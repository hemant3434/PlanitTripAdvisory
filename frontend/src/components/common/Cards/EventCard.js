import React from 'react';
import ImageCard from './ImageCard';
import TravelCard from './TravelCard';

var images = {
  toronto: require("./../../../assets/images/toronto.jpg")
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
          text={this.props.common.description}
          map={this.props.common.id}/>;
    }
  }
}
