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
<<<<<<< HEAD
          map={this.props.common.id}/>;
=======
          map={this.props.common.id}
          cost={this.props.common.price}
          time={this.props.common.expectedLength}/>;
>>>>>>> f0ca4c1b9275c4154d4ca9092a2e2eaff9da0704
    }
  }
}
